package app.controllers;

import app.domain.TimedSession;
import app.domain.User;
import app.model.TimedSessionDTO;
import app.repositories.TimedSessionRepository;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by softish on 2017-10-04.
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private TimedSessionRepository timedSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<TimedSessionDTO> saveSession(@RequestBody TimedSessionDTO timedSessionDTO) {
        if(timedSessionDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findOne(timedSessionDTO.getUserId());
        TimedSession timedSession = new TimedSession(user, timedSessionDTO.getDuration(), timedSessionDTO.getTask());
        timedSessionRepository.save(timedSession);

        return null;
    }
}
