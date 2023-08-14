<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:authed>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Class</th>
                    <th scope="col">Status</th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <td class="class-name">COMPSCI 711</td>
                    <td class="class-status">Open</td>
                </tr>
                <tr>
                    <td class="class-name">COMPSCI 712</td>
                    <td class="class-status">Open</td>
                </tr>
            </tbody>
        </table>
</t:authed>
