package app.controllers;

import app.domain.TimedSession;
import app.domain.TimedSessionStatistic;
import app.domain.User;
import app.model.AuthenticatedUserDTO;
import app.model.SessionRangeDTO;
import app.model.TimedSessionDTO;
import app.model.TimedSessionStatisticDTO;
import app.repositories.TimedSessionRepository;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

    @RequestMapping(value = "/getRange", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<List<TimedSessionStatisticDTO>> getRangeOfSessions(@RequestBody SessionRangeDTO sessionRangeDTO) {
        if (sessionRangeDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findOne(sessionRangeDTO.getUserId());
        List<TimedSessionStatistic> timedSessionStatistics = timedSessionRepository.getCountInRange(user, sessionRangeDTO.getEndOfRangeDate());

        if (timedSessionStatistics != null && timedSessionStatistics.size() > 0) {
            List<TimedSessionStatisticDTO> timedSessionStatisticDTOS = new ArrayList<>();

            for (TimedSessionStatistic timedSessionStatistic: timedSessionStatistics) {
                timedSessionStatisticDTOS.add(new TimedSessionStatisticDTO(
                        timedSessionStatistic.getUser().getId(),
                        timedSessionStatistic.getEndDateTime().toString().substring(0, 10),
                        millisToMinutes(timedSessionStatistic.getTotalDuration())));
            }

            timedSessionStatisticDTOS = padDates(timedSessionStatisticDTOS, sessionRangeDTO);
            return new ResponseEntity<>(timedSessionStatisticDTOS, HttpStatus.OK);
        }

        return new ResponseEntity<>(padDates(new ArrayList<>(), sessionRangeDTO), HttpStatus.OK);
    }

    private long millisToMinutes(long millis) {
        return millis / (60 * 1000);
    }

    private List<TimedSessionDTO> convertToDTOList(List<TimedSession> timedSessions) {
        List<TimedSessionDTO> timedSessionDTOs = new ArrayList<>();

        for (TimedSession timedSession : timedSessions) {
            timedSessionDTOs.add(new TimedSessionDTO(timedSession.getUser().getId(), millisToMinutes(timedSession.getDuration()), timedSession.getTask(), timedSession.getEndDateTime()));
        }

        return timedSessionDTOs;
    }

    private List<TimedSessionStatisticDTO> padDates(List<TimedSessionStatisticDTO> timedSessionStatisticDTOs, SessionRangeDTO sessionRangeDTO) {
        List<TimedSessionStatisticDTO> paddedResult = new ArrayList<>();
        LocalDate dateInRange = sessionRangeDTO.getEndOfRangeDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int i = 0;
        while(i < timedSessionStatisticDTOs.size()) {
            LocalDate dateInResultSet = LocalDate.parse(timedSessionStatisticDTOs.get(i).getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (dateInRange.compareTo(dateInResultSet) < 0) {
                paddedResult.add(new TimedSessionStatisticDTO(sessionRangeDTO.getUserId(), dateInRange.toString(), 0));
            } else if (dateInRange.compareTo(dateInResultSet) == 0) {
                paddedResult.add(timedSessionStatisticDTOs.get(i));
                i++;
            }

            dateInRange = dateInRange.plusDays(1);
        }

        while (dateInRange.compareTo(LocalDate.now()) <= 0) {
            paddedResult.add(new TimedSessionStatisticDTO(sessionRangeDTO.getUserId(), dateInRange.toString(), 0));
            dateInRange = dateInRange.plusDays(1);
        }

        return paddedResult;
    }
}
