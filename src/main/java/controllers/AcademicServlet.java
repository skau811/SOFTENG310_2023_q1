package controllers;

import backend.User;
import backend.UserAccessLevel;
import models.Course;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AcademicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();

        if (url.equals("/AvailableClasses")) {
            doPost(request, response);
        }else if (url.equals("/AcademicProfile")){
            doPost(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        User user = new User(username, password, UserAccessLevel.STUDENT, new ArrayList<>());

            if (url.equals("/AcademicProfile")){
            if(!username.matches("[a-zA-Z][a-zA-Z-][a-zA-Z][a-zA-Z][0-9]{3}")){
                response.sendRedirect("/");
                dispatcher = getServletContext().getRequestDispatcher("/");
            } else {
                user.setUsername(username);
                session.setAttribute("userObj", user);
                session.setAttribute("user", username);
            }

            if(!password.matches("^(?=.*[a-zA-Z\\d].*)[a-zA-Z\\d!@#$%&*]{7,}$")) {
                response.sendRedirect("/");
                dispatcher = getServletContext().getRequestDispatcher("/");
            }

            String userType = request.getParameter("usertype");
            if(userType == ""){
                response.sendRedirect("/");
                dispatcher = getServletContext().getRequestDispatcher("/");
            }
            else {
                switch (userType){
                    case "1" : session.setAttribute("accessLevel", "Student"); user.setAccessLevel(UserAccessLevel.STUDENT); break;
                    case "2" : session.setAttribute("accessLevel", "Lecturer"); user.setAccessLevel(UserAccessLevel.LECTURER); break;
                    case "3" : session.setAttribute("accessLevel", "Staff Administrator"); user.setAccessLevel(UserAccessLevel.STAFF_ADMINISTRATOR); break;
                }
            }
            session.setAttribute("userObj", user);
            session.setAttribute("user", username);
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/templates/AcademicProfile.jsp");
        }else if (url.equals("/addClass")){
            String className = request.getParameter("class");
            Course course = new Course(className, 20);
            user.applyForClass(course);
            session.setAttribute("userObj", user);
            System.out.println(className);
        }
        //forward the request and the response parameters to the dispatcher
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
    }
}
