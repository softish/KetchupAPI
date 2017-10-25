package app.controllers;

import app.domain.TimedSession;
import app.domain.User;
import app.model.AuthenticatedUserDTO;
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

import java.util.List;

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
        TimedSession timedSession = new TimedSession(user, timedSessionDTO.getDuration(), timedSessionDTO.getTask(), timedSessionDTO.getEndDateTime());
        timedSessionRepository.save(timedSession);

        return null;
    }

    @RequestMapping(value = "/getLatest", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<TimedSessionDTO> getLatestSession(@RequestBody AuthenticatedUserDTO authenticatedUserDTO) {
        if(authenticatedUserDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<TimedSession> timedSessions = timedSessionRepository.findByUserId(authenticatedUserDTO.getId());
        if(timedSessions != null && timedSessions.size() > 0) {
            TimedSession timedSession = timedSessions.get(timedSessions.size() - 1);
            TimedSessionDTO timedSessionDTO = new TimedSessionDTO(timedSession.getUser().getId(), millisToMinutes(timedSession.getDuration()), timedSession.getTask(), timedSession.getEndDateTime());
            return new ResponseEntity<>(timedSessionDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private long millisToMinutes(long millis) {
        return millis / (60 * 1000);
    }
}
