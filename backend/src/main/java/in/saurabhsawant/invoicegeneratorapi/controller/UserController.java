package in.saurabhsawant.invoicegeneratorapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import in.saurabhsawant.invoicegeneratorapi.entity.User;
import in.saurabhsawant.invoicegeneratorapi.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public User createorUpadteUser(@RequestBody User user, Authentication authentication) {
		/*
		 * System.out.println(user); System.out.println("From token: " +
		 * authentication.getName()); System.out.println("From body: " +
		 * user.getClerkId());
		 */
		try {
			if (!authentication.getName().equals(user.getClerkId())) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN,
						"User does not have permission to access this resource");
			}
			return userService.saveOrUpdateUser(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
