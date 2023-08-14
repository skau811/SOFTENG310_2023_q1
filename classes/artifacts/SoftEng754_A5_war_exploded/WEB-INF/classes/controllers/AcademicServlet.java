package controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        if (url.equals("/AvailableClasses")){
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/templates/AvailableClasses.jsp");
        }else if (url.equals("/AcademicProfile")){
            if(!username.matches("[a-zA-Z][a-zA-Z-][a-zA-Z][a-zA-Z][0-9]{3}")){
                dispatcher = getServletContext().getRequestDispatcher("/");
            } else {
                session.setAttribute("user", username);
            }

            if(!password.matches("^(?=.*[a-zA-Z\\d].*)[a-zA-Z\\d!@#$%&*]{7,}$")) {
                dispatcher = getServletContext().getRequestDispatcher("/");
            }

            session.setAttribute("user", username);

            String userType = request.getParameter("usertype");
            if(userType == ""){
                dispatcher = getServletContext().getRequestDispatcher("/");
            }
            else {
                switch (userType){
                    case "1" : session.setAttribute("accessLevel", "Student"); break;
                    case "2" : session.setAttribute("accessLevel", "Lecturer"); break;
                    case "3" : session.setAttribute("accessLevel", "Staff Administrator"); break;
                }
            }
            session.setAttribute("user", username);
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/templates/AcademicProfile.jsp");
        }
        //forward the request and the response parameters to the dispatcher
        dispatcher.forward(request, response);
    }
}
