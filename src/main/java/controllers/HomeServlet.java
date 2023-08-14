package controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

 public class HomeServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
            //forward the request and the response parameters to the dispatcher
            dispatcher.forward(request, response);
        }

         @Override
         protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
             //forward the request and the response parameters to the dispatcher
             dispatcher.forward(request, response);
         }
}


