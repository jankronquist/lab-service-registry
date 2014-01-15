package com.jayway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Messages {
    public static Object gameCreatedEvent(String gameId, String createdBy, List<String> players) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("createdBy", createdBy);
        body.put("players", players);
        body.put("gameType", "rock-paper-scissors");
        body.put("gameUrl", "http://rps.com/games/" + gameId);

        return createMessage(gameId, "GameCreatedEvent", body);
    }

    public static Object gameEndedEvent(String gameId, Map<String, Number> scores) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("scores", scores);
        body.put("result", "true");
        body.put("gameType", "rock-paper-scissors");

        return createMessage(gameId, "GameEndedEvent", body);
    }

    public static Object serviceOnlineEvent(String serviceId, String name, String entryPoint, String createdBy) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("createdBy", createdBy);
        body.put("entryPoint", entryPoint);

        return createMessage(serviceId, "ServiceOnlineEvent", body);
    }

    // TODO: level or severity??

    public static Object logEvent(String serviceId, LogLevel level, String context, String message) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("level", level);
        body.put("context", context);
        body.put("message", message);
        return createMessage(serviceId, "LogEvent", body);
    }

    public static Object serviceOfflineEvent(String serviceId) throws Exception {
        Map<String, Object> body = new HashMap<>();
        return createMessage(serviceId, "ServiceOfflineEvent", body);
    }

    private static Object createMessage(String streamId, String type, Map<String, Object> body) {
        Map<String, Object> message = new HashMap<String, Object>();
        Map<String, Object> meta = new HashMap<>();
        message.put("type", type);
        message.put("body", body);
        message.put("meta", meta);
        message.put("streamId", streamId);
        message.put("createdAt", System.currentTimeMillis());
        return message;
    }
}
