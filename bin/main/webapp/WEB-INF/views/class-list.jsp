<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:authed>

    <div class="mb-5">
        <h2>Enrolled Classes</h2>
        <table class="table enrolled-classes">
            <thead>
                <tr>
                    <th scope="col">Class</th>
                    <th scope="col">Status</th>
                    <th scope="col">No. of Seats</th>
                    <th scope="col">No. of Waitlisted Students</th>
                    <th scope="col">Notes</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${enrolledCourses}" varStatus="loop">
                <tr id="enrolledCourseRow${loop.index}">
                    <td class="class-name">${course.getName()}</td>
                    <td class="class-status">${course.getStatus()}</td>
                    <td class="class-seats">${course.getRemainingSeats()}</td>
                    <td class="class-waitlisted-seats">10</td>
                    <td class="class-note">${course.getNotes()}</td>
                    <td class="class-actions">
                        <c:if test="${accessLevel == 'Student'}">
                            <button type="button"
                                    class="btn btn-sm btn-danger btn-drop-class"
                                    onclick="dropEnrolledCourse(${loop.index})">Drop Class</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${userObj.classList != null}">
                <c:forEach var="course" items="${userObj.classList}">
                    <tr>
                        <td class="class-name">${course.getName()}</td>
                        <td class="class-status">${course.getStatus()}</td>
                        <td class="class-seats">${course.getRemainingSeats()}</td>
                        <td class="class-waitlisted-seats">10</td>
                        <td class="class-note">${course.getNotes()}</td>
                        <td class="class-actions">
                            <c:if test="${accessLevel == 'Student'}">
                                <button type="button"
                                        class="btn btn-sm btn-danger btn-drop-class"
                                        onclick="dropEnrolledCourse(${loop.index})">Drop Class</button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>

 
    <div>
        <h2>Active Concessions</h2>

        <table class="table active-concessions">
            <thead>
            <tr>
                <th scope="col">Class</th>
                <th scope="col">Reasonale</th>
                <th scope="col">Status</th>
                <th scope="col">Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="concession" items="${activeConcessions}" varStatus="loop">
        <tr id="activeConcessionRow${loop.index}">
            <td class="concession-class">${concession.getName()}</td>
            <td class="concession-reasonale">${concession.getNotes()}</td>
            <td class="concession-status" id="${concession.getName()}" value="Submitted">Submitted</td>

            <td class="concession-actions">
                <c:if test="${accessLevel != 'Student'}">
                    <button type="button" class="btn btn-primary btn-approve" id="${concession.getName()}ApproveBtn" onclick="approveConcession('${concession.getName()}')">Approve</button>
                    <button type="button" class="btn btn-danger btn-decline" id="${concession.getName()}DeclineBtn" onclick="declineConcession('${concession.getName()}')">Decline</button>
                </c:if>
                <c:if test="${accessLevel == 'Student'}">
                    <button type="button"
                            class="btn btn-danger btn-revoke-application"
                            onclick="revokeApplication('${loop.index}')">Revoke Application</button>
                </c:if>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="mb-3">
        <h2>Available Classes</h2>
        <div class="form-group">
            <label for="availableClassFilter">Class Filter</label>
            <select class="form-control" id="availableClassFilter" onchange="onFilterChange(this)">
                <option value="none">None</option>
                <option value="OPSMGT">OPSMGT</option>
            </select>
        </div>
        <table class="table active-classes">
            <thead>
            <tr>
                <th scope="col">Class</th>
                <th scope="col">Status</th>
                <th scope="col">Concession Status</th>
                <th scope="col">No. of Seats</th>
                <th scope="col">No. of Waitlisted Students</th>
                <th scope="col">Notes</th>
                <th scope="col">Timetable</th>
                <th scope="col">Alternative Timetable</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="course" items="${availableCourses}">
                <tr>
                    <td class="class-name">${course.getName()}</td>
                    <td class="class-status">${course.getStatus()}</td>
                    <td class="class-concessionStatus">Unavailable</td>
                    <td class="class-seats">${course.getRemainingSeats()}</td>
                    <td class="class-waitlisted-seats">10</td>
                    <td class="class-note">${course.getNotes()}</td>
                    <td class="class-timetable" id="Timetable">${course.getTimetable()}</td>
                    <td class="class-alt-timetable" id="AltTimetable">${course.getAltTimetable()}</td>
                    <td class="class-actions">
                        <button type="button" id="${course.getName()}EnrolBtn" class="btn btn-primary btn-enroll" onclick="applyForClass(event)">Enroll</button>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td class="class-name">COMPSCI 711</td>
                <td class="class-status">Open</td>
                <td class="class-concessionStatus">Unavailable</td>
                <td class="class-seats">20</td>
                <td class="class-waitlisted-seats">10</td>
                <td class="class-note">-</td>
                <td class="class-timetable" id="Timetable">Timetable Everyday</td>
                <td class="class-alt-timetable" id="AltTimetable"></td>
                <td class="class-actions">
                    <a id="COMPSCI 711ConcessionLink" class="btn btn-primary btn-enroll" onclick="applyForConcession(event)">Request Concession</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</t:authed>

<script type="application/javascript">
    function approveConcession(courseName) {
        document.getElementById(courseName).setAttribute("value", "Approved");
        document.getElementById(courseName).innerHTML = "Approved";
    }

    function declineConcession(courseName) {
        document.getElementById(courseName).setAttribute("value", "Declined");
        document.getElementById(courseName).innerHTML = "Declined";
    }

    function dropEnrolledCourse(rowNumber) {
        let tableRow = document.getElementById('enrolledCourseRow' + rowNumber);
        tableRow.remove();

        let selectedCourseRemainingSeats = Number(tableRow.querySelector("td.class-seats").innerHTML);
        selectedCourseRemainingSeats++;

        let waitlistedSeats = Number(tableRow.querySelector("td.class-waitlisted-seats").innerHTML);
        waitlistedSeats--;

        if (selectedCourseRemainingSeats > 0) {
            tableRow.querySelector("td.class-status").innerHTML = "Open";
            tableRow.querySelector("td.class-note").innerHTML = "";
        }

        tableRow.querySelector("td.class-seats").innerHTML = selectedCourseRemainingSeats.toString();
        tableRow.querySelector("td.class-waitlisted-seats").innerHTML = waitlistedSeats.toString();

        tableRow.lastElementChild.innerHTML = "";

        let availableCoursesTable = document.querySelector("table.active-classes tbody");
        availableCoursesTable.append(tableRow);
    }

    function revokeApplication(rowNumber) {
        let tableRow = document.getElementById('activeConcessionRow' + rowNumber);
        tableRow.remove();
    }

    function onFilterChange(e) {
        const value = e.value;

        let availableClasses = document.querySelectorAll("table.active-classes tbody > tr");

        availableClasses.forEach(x => {
            if (!x.innerText.includes("OPSMGT")) {
                x.remove();
            }
        });
    }
</script>
