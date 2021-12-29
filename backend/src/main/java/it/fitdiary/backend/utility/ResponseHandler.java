package it.fitdiary.backend.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, String objectName, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        String statusMessage = "";
        if (status.is1xxInformational() || status.is2xxSuccessful() || status.is3xxRedirection()) {
            Map<String, Object> objectMap = new HashMap<String, Object>();
            objectMap.put(objectName, responseObj);
            map.put("data", objectMap);
            statusMessage = "success";
        }
        else if (status.is4xxClientError()) {
            map.put("data", responseObj);
            statusMessage = "fail";
        }
        map.put("status", statusMessage);
        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", "error");
        return new ResponseEntity<Object>(map,status);
    }
}