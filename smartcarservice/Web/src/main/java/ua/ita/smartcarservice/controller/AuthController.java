package ua.ita.smartcarservice.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.ita.smartcarservice.dto.LoginForm;
import ua.ita.smartcarservice.dto.SignUpForm;
import ua.ita.smartcarservice.entity.RoleEntity;
import ua.ita.smartcarservice.entity.RoleName;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.repository.RoleRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.security.JwtResponse;
import ua.ita.smartcarservice.security.ResponseMessage;
import ua.ita.smartcarservice.security.TokenProvider;





@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenProvider tokenProvider;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
	
             SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.generateToken(authentication);
		
		UserDetails userDetail = (UserDetails) authentication.getPrincipal();
		System.out.println("userDetail(useName) : " + userDetail.getUsername());
	   System.out.println("userDetail: " + userDetail);
		
		
		return ResponseEntity.ok(new JwtResponse(jwt, userDetail.getUsername(), userDetail.getAuthorities()));
		
		
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm registerRequest){
		
		if(userRepository.existsByUsername(registerRequest.getUsername())) {
			
			return new ResponseEntity<>(new ResponseMessage("Fail: Username is already exists!"), HttpStatus.BAD_REQUEST);
			
		}
		
		UserEntity userEntity = new UserEntity(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()),
				registerRequest.getEmail(), registerRequest.getFullName(), registerRequest.getNumberPhone());
		
		Set<String> strRoles = registerRequest.getRole();
		Set<RoleEntity> roles = new HashSet<RoleEntity>();
         System.out.println(strRoles);
		strRoles.forEach(role -> {
			switch (role) {
			case "ADMIN":
				RoleEntity adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Admin Role not find."));
				roles.add(adminRole);
 
				break;
			case "SALES_MANAGER":
				RoleEntity saleManagerRole = roleRepository.findByName(RoleName.ROLE_SALES_MANAGER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: SaleManager Role not find."));
				roles.add(saleManagerRole);
 
				break;
			case "TECHNICAL_MANAGER":
				RoleEntity technicalManagerRole = roleRepository.findByName(RoleName.ROLE_TECHNICAL_MANAGER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: TechnicalManager Role not find."));
				roles.add(technicalManagerRole);
 
				break;
			case "DIELER":
				RoleEntity dielerRole = roleRepository.findByName(RoleName.ROLE_DIELER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: DIELER Role not find."));
				roles.add(dielerRole);
 
				break;
			case "WORKER":
				RoleEntity workerRole = roleRepository.findByName(RoleName.ROLE_WORKER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: WORKER Role not find."));
				roles.add(workerRole);

				break;

			default:
				RoleEntity carOwnerROle = roleRepository.findByName(RoleName.ROLE_CAR_OWNER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(carOwnerROle);
				
				break;

			}
		});
		
		userEntity.setRoles(roles);
		System.out.println(userEntity);
		userRepository.save(userEntity);
		
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
		
	}

}
