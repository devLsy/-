package com.test.lsy.sessiontest1.session;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;

@RestController
@Slf4j
public class SessionController {

    // 세션에 데이터 저장
    @PostMapping("/session/set")
    public String setSession(@RequestParam String key, @RequestParam String value, HttpSession session) {
        session.setAttribute(key, value);  // 세션에 key-value 저장
        return "Session data saved: " + key + " = " + value;
    }

    // 세션에서 데이터 조회
    @GetMapping("/session/get")
    public String getSession(@RequestParam String key, HttpSession session) {
        Object value = session.getAttribute(key);  // 세션에서 데이터 조회
        if (value != null) {
            return "Session data: " + key + " = " + value;
        } else {
            return "No data found for key: " + key;
        }
    }

    // 세션 삭제
    @DeleteMapping("/session/remove")
    public String removeSession(@RequestParam String key, HttpSession session) {
        session.removeAttribute(key);  // 세션에서 데이터 삭제
        return "Session data removed for key: " + key;
    }

    // 세션에 저장된 모든 데이터 출력
    @GetMapping("/session/all")
    public String getAllSessionData(HttpSession session) {
        Enumeration<String> attributeNames = session.getAttributeNames(); // 세션에 저장된 모든 속성 이름
        StringBuilder sessionData = new StringBuilder("All session data:\n");

        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            Object value = session.getAttribute(key);
            sessionData.append(key).append(" = ").append(value).append("\n");
        }

        if (sessionData.length() == "All session data:\n".length()) {
            return "No session data found.";
        }

        return sessionData.toString();
    }
}
