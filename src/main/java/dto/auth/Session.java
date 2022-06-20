package dto.auth;

import dto.UserDTO;
import dto.project.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Session {
    public static SessionUser sessionUser;
    public static SessionProject sessionProject;

    public static void setSessionUser(UserDTO session) {
        sessionUser = new SessionUser(session);
    }

    public static void setSessionProject(ProjectDTO session) {
        sessionProject = new SessionProject(session);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class SessionUser {
        private Long id;
        private String username;

        public SessionUser(UserDTO session) {
            this.id = session.getId();
            this.username = session.getUsername();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class SessionProject {
        private Long id;
        private String title;

        public SessionProject(ProjectDTO session) {
            this.id = session.getId();
            this.title = session.getTitle();
        }
    }
}
