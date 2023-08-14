<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:authed>
    <div class="text-center">
        <h1>Academic Profile:</h1>
        <h3>
            <div style="font-style: italic" id="currentUser">${accessLevel} : ${user}</div>
            <a id="availableClasses" href="/AvailableClasses">View available classes</a>
        </h3>
    </div>
</t:authed>






