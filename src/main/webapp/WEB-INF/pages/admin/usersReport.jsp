<%@include file="../header.jsp"%>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">LMS system</a>
        </div>
        <div class="">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/admin/students">Students</a></li>
                <li><a href="/admin/librarians">Librarians</a></li>
                <li><a href="/admin/administrators">Administrators</a></li>
                <li><a href="/admin/books">Books</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container ">

        <button class="btn btn-primary btn-xs" onclick="orderBy('PRESENT')" >Order By Present Days</button>
        <button class="btn btn-primary btn-xs" onclick="orderBy('MISSED')" >Order By Missed Days</button>
        <button class="btn btn-primary btn-xs" onclick="orderBy('TOTAL')" >Order By Total Hours</button>

</div>

<div class="row">
    <div class="col-md-1">

    </div>
    <div class="col-md-10">
        <table class="table table-hover" id="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">First name</th>
                <th scope="col">Last name</th>
                <th scope="col">Present Days</th>
                <th scope="col">Missed Days</th>
                <th scope="col">Total Worked Hours</th>
            </tr>
            </thead>
            <tbody>
            <%int counter=1; %>
            <c:forEach items="${usersReport}" var="userReport">
                <tr id="row<%=counter%>">
                    <th scope="row"><%= counter %></th>
                    <td scope="row">${userReport.firstName}</td>
                    <td scope="row">${userReport.lastName}</td>
                    <td scope="row">${userReport.presentDays}</td>
                    <td scope="row">${userReport.missedDays}</td>
                    <td scope="row">${userReport.allHours}</td>
                </tr>
                <% counter++; %>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-1">

    </div>
</div>

<form id="orderBy" action="/users/usersReport" method="post" hidden>
    <input type="text" value="" name="order" id="orderByInput">
</form>

<script type="text/javascript">
    function orderBy(order){
        document.getElementById('orderByInput').value=order;
        document.getElementById('orderBy').submit();
    }
</script>

</body>
</html>