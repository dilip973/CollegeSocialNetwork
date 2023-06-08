package com.socialnetwork.main.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.main.bto.Message;
import com.socialnetwork.main.model.ApprovedNewsFeed;
import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Event;
import com.socialnetwork.main.model.Faculty;
import com.socialnetwork.main.model.Post;
import com.socialnetwork.main.model.Student;
import com.socialnetwork.main.model.User;
import com.socialnetwork.main.repository.ApprovedNewsFeedRepository;
import com.socialnetwork.main.repository.ApprovedRegistrationRepository;
import com.socialnetwork.main.repository.EventRepository;
import com.socialnetwork.main.repository.FacultyRepository;
import com.socialnetwork.main.repository.PostRepository;
import com.socialnetwork.main.repository.StudentRepository;
import com.socialnetwork.main.repository.UserRepository;

//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.socialnetwork.main.bto.Message;
//import com.socialnetwork.main.model.User;
//import com.socialnetwork.main.repository.UserRepository;
//
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/register")
//    public ResponseEntity<Object> register(@RequestBody User user) {
//        User uDB = userRepository.findByEmailId(user.getEmailId());
//        Message m = new Message();
//
//        if (uDB != null) {
//            m.setMsg("Email already registered");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
//        }
//
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        userRepository.save(user);
//
//        m.setMsg("User registration successful");
//
//        return ResponseEntity.status(HttpStatus.OK).body(m);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Object> login(@RequestBody User user, HttpSession session, HttpServletResponse response) {
//
//        User uDB = userRepository.findByEmailId(user.getEmailId());
//        Message m = new Message();
//
//        if (uDB != null && passwordEncoder.matches(user.getPassword(), uDB.getPassword())) {
//            session.setAttribute("loggedInUser", uDB);
//           
//            return ResponseEntity.status(HttpStatus.OK).body(uDB);
//        } else {
//            m.setMsg("Invalid email or password.");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
//        }
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        session.removeAttribute("loggedInUser");
//        session.invalidate();
//
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                cookie.setMaxAge(0);
//                response.addCookie(cookie);
//            }
//        }
//
//        Message message = new Message();
//        message.setMsg("User logged out successfully.");
//
//        return ResponseEntity.ok(message);
//    }
//
//    @GetMapping("/login")
//    public User login(Principal principal) {
//        User user = userRepository.getUserByEmailId(principal.getName());
//        return user;
//    }
//}
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ApprovedRegistrationRepository approvedRegistrationRepository;

	@Autowired
	private ApprovedNewsFeedRepository approvedNewsFeedRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private FacultyRepository facultyRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(HttpSession session) {
		if (isLoggedIn(session)) {
			List<Student> students = studentRepository.findAll();
			return ResponseEntity.ok(students);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/registrations")
	public ResponseEntity<List<ApprovedRegistration>> getAllRegistrations(HttpSession session) {
		if (isLoggedIn(session)) {
			List<ApprovedRegistration> registrations = approvedRegistrationRepository.findAll();
			return ResponseEntity.ok(registrations);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/newsfeed")
	public ResponseEntity<List<ApprovedNewsFeed>> getAllNewsFeed(HttpSession session) {
		if (isLoggedIn(session)) {
			List<ApprovedNewsFeed> newsFeed = approvedNewsFeedRepository.findAll();
			return ResponseEntity.ok(newsFeed);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/events")
	public ResponseEntity<List<Event>> getAllEvents(HttpSession session) {
		if (isLoggedIn(session)) {
			List<Event> events = eventRepository.findAll();
			return ResponseEntity.ok(events);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/faculties")
	public ResponseEntity<List<Faculty>> getAllFaculties(HttpSession session) {
		if (isLoggedIn(session)) {
			List<Faculty> faculties = facultyRepository.findAll();
			return ResponseEntity.ok(faculties);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPosts(HttpSession session) {
		if (isLoggedIn(session)) {
			List<Post> posts = postRepository.findAll();
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	private boolean isLoggedIn(HttpSession session) {
		return session.getAttribute("loggedInUser") != null;
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody User user, HttpSession session, HttpServletResponse response) {

		User uDB = userRepository.findByEmailId(user.getEmailId());
		Message m = new Message();

		if (uDB != null && passwordEncoder.matches(user.getPassword(), uDB.getPassword())) {
			session.setAttribute("loggedInUser", uDB);

			return ResponseEntity.status(HttpStatus.OK).body(uDB);
		} else {
			m.setMsg("Invalid email or password.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
		}
	}

	@GetMapping("/login")
	public User login(Principal principal) {
		User user = userRepository.getUserByEmailId(principal.getName());
		return user;
	}
	
	public void setStudentRepository(StudentRepository studentRepository) {
		// TODO Auto-generated method stub
		this.studentRepository = studentRepository;
		
	}

	public void setApprovedRegistrationRepository(ApprovedRegistrationRepository approvedRegistrationRepository) {
		// TODO Auto-generated method stub
		this.approvedRegistrationRepository=approvedRegistrationRepository;
		
	}

	public void setApprovedNewsFeedRepository(ApprovedNewsFeedRepository approvedNewsFeedRepository) {
		// TODO Auto-generated method stub
		this.approvedNewsFeedRepository=approvedNewsFeedRepository;
		
	}

	public void setEventRepository(EventRepository eventRepository) {
		// TODO Auto-generated method stub
		this.eventRepository=eventRepository;
		
	}

	public void setFacultyRepository(FacultyRepository facultyRepository) {
		// TODO Auto-generated method stub
		this.facultyRepository=facultyRepository;
		
	}

	public void setPostRepository(PostRepository postRepository) {
		// TODO Auto-generated method stub
		this.postRepository=postRepository;
		
	}

	public void setUserRepository(UserRepository userRepository) {
		// TODO Auto-generated method stub
		this.userRepository=userRepository;
		
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		// TODO Auto-generated method stub
		this.passwordEncoder=passwordEncoder;
		
	}
}
