package controllers;

import models.Course;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> availableCourses = new ArrayList<>();
        availableCourses.add(new Course("OPSMGT 250", 0));
        availableCourses.add(new Course("INFOSYS 735", 0));

        Course availableCourse = new Course("COMPSCI 711", 10);
        availableCourse.setTimetable("Lectures: Monday 3pm-5pm and Thursday 2pm-4pm, Tutorial: Thursday 1pm-2pm");
        availableCourse.setAltTimetable("Lectures: Monday 1pm-3pm and Thursday 6pm-8pm");

        Course availableCourse2 = new Course("COMPSCI 712", 15);
        availableCourse2.setTimetable("Lectures: Monday 3pm-5pm and Thursday 2pm-4pm, Tutorial: Thursday 1pm-2pm");

        Course availableCourse3 = new Course("COMPSCI 712", 24);
        availableCourse3.setTimetable("Lectures: Monday 3pm-5pm and Thursday 2pm-4pm, Tutorial: Thursday 1pm-2pm");

        availableCourses.add(availableCourse);
        availableCourses.add(availableCourse2);
        availableCourses.add(availableCourse3);

        List<Course> enrolledCourses = new ArrayList<>();
        enrolledCourses.add(new Course("COMPSCI 732 / SOFTENG 750", 10));
        enrolledCourses.add(new Course("SOFTENG 754", 0));
        enrolledCourses.add(new Course("Full Class", 0));
        enrolledCourses.add(new Course("OPSMGT 757", 0));

        List<Course> activeConcessions = new ArrayList<>();
        Course concCourse = new Course("SoftEng 754", 0);
        Course concCourse2 = new Course("SoftEngOver 9000", 10);
        concCourse.setNotes("Failed to meet Assignment 5 deadline #totalScrubs");
        concCourse2.setNotes("It's over 9000");
        activeConcessions.add(concCourse);
        activeConcessions.add(concCourse2);

        request.setAttribute("availableCourses", availableCourses);
        request.setAttribute("enrolledCourses", enrolledCourses);
        request.setAttribute("activeConcessions", activeConcessions);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/class-list.jsp");
        //forward the request and the response parameters to the dispatcher
        dispatcher.forward(request, response);
    }
}
