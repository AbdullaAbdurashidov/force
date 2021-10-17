<%@ include file="../header.jsp" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request"/>

<style>
    html{
        height: 100%;
        width: 100%;
    }
    body{
        height: 100%;
        width: 100%;
    }
    <%@include file="../css/add&editFormStyles.css"%>
</style>

<title>Administrator Page | Students</title>
</head>

<body>

<div id="body">
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
        <div class="btn-group" >
            <a href="#"><button class="btn btn-success btn-xs" onclick="openAddUserForm()">Add User</button></a>
            <a href="#"><button class="btn btn-info btn-xs" onclick="openEditUserForm()">Edit User</button></a>
            <a href="#"><button class="btn btn-danger btn-xs" onclick="deleteUser()">Delete User</button></a>
            <a id="imagePath" href="" download><button type="button" onclick="downloadImage()" class="btn btn-primary btn-xs" >Download Image</button></a>
            <a id="documentPath" href="" download><button type="button" onclick="downloadDocument()" class="btn btn-primary btn-xs" >Download Document</button></a>
            <a href="#"><button type="submit" onclick="toExcel()" class="btn btn-primary btn-xs">To Excel</button></a>
            <a href="#"><button type="submit" onclick="toWord()" class="btn btn-primary btn-xs">To Word</button></a>
            <a href="#"><button type="submit" onclick="toPDF()" class="btn btn-primary btn-xs">To PDF</button></a>
            <a href="#"><button class="btn btn-warning btn-xs" onclick="getUserTime()">See User Time</button></a>
            <a href="#">
                <form action="/users/usersReport" method="post" style="display: inline" >
                    <button class="btn btn-warning btn-xs" type="submit">Get all Users Report</button>
                </form>
            </a>
        </div>
    </div>



    <div style="position: absolute; top: 5px;right: 2%">
        <form id="setUserTime" action="/users/setTime" method="post" style="display: inline-block">
            <input type="text" name="userID" id="setTimeUserID" hidden>
            <input type="text" name="role" value="STUDENT"  hidden>
            <label for="datePicker">Date</label>
            <input type="date" name="datePicker" class="form-control" id="datePicker" style="width: 28%;display: inline;">
            <label for="timepickerArrival">Arrived at</label>
            <input type="time" id="timepickerArrival" name="timepickerArrival" class="form-control timepicker" style="width: 20%;display: inline;">
            <label for="timepickerLeaving">Left at</label>
            <input type="time" id="timepickerLeaving" name="timepickerLeaving" class="form-control timepicker" style="width: 20%;display: inline;">
        </form>
        <button class="btn btn-info" onclick="setUserTime()" style="display: inline">Set</button>
    </div>

    <div class="row">
        <div class="col-md-1">

        </div>
        <div class="col-md-10">
            <table class="table table-hover" id="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Photo</th>
                    <th scope="col">FirstName</th>
                    <th scope="col">LastName</th>
                    <th scope="col">UserName</th>
                    <th scope="col">Password</th>
                    <th scope="col">Role</th>
                    <th scope="col">Blocked</th>
                    <th scope="col"> </th>
                </tr>
                </thead>
                <tbody>
                <%int counter=1; %>
                <c:forEach items="${users}" var="user">
                    <tr id="row<%=counter%>">
                        <th scope="row"><%= counter %></th>
                        <td scope="row" hidden>${user.userID}</td>
                        <th scope="row"><img src="${cp}${user.imagePath}" width="50px"></th>
                        <td scope="row">${user.firstName}</td>
                        <td scope="row">${user.lastName}</td>
                        <td scope="row">${user.userName}</td>
                        <td scope="row">${user.password}</td>
                        <td scope="row">${user.role}</td>
                        <td scope="row" class="bg-<c:if test="${user.blocked == 0}">${"success"}</c:if><c:if test="${user.blocked == 1}">${"danger"}</c:if>">${user.blocked}</td>
                        <th scope="row" hidden>${cp}${user.documentPath}</th>
                    </tr>
                    <% counter++; %>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-1">
        </div>
    </div>





</div>

<div id="none" style="display: none">
</div>

<%@include file="../forms/editUserForm.jsp"%>
<%@include file="../forms/addUserForm.jsp"%>

<form action="/users/delete" id="deleteForm" method="post">
    <input type="text" value="" name="userID" id="userID" hidden>
    <input type="text" value="STUDENT" name="role" id="deleteRole" hidden>
</form>
<form action="/users/excel/download" id="toExcel" method="get">
    <input type="text" name="role" value="STUDENT" hidden>
</form>
<form action="/users/word/download" id="toWord" method="get">
    <input type="text" name="role" value="STUDENT" hidden>
    <input type="text" name="userID" id="wordUserID" value="STUDENT" hidden>
</form>
<form action="/users/pdf/download" id="toPDF" method="get">
    <input type="text" name="role" value="STUDENT" hidden>
    <input type="text" name="userID" id="pdfUserID" value="STUDENT" hidden>
</form>
<form id="getUserTimeByID" action="/users/userTime" method="post" hidden>
    <input name="userID" id="getUserTimeUserID" value=""/>
</form>



<script type="text/javascript">
    $('td').click(function(){
        let row_index = $(this).parent().index();
        let row = document.getElementById("row" + (row_index + 1));
        if(clicked==0) {
            row.setAttribute("style", "background-color: lightgray");
            clickedRow=row_index+1;
            clicked++;
        }
        else if(clicked==1 & row.hasAttribute("style")){
            clicked--;
            clickedRow=-1;
            row.removeAttribute("style");
        }
        else if(clicked==1){
            document.getElementById("row"+(clickedRow)).removeAttribute("style");
            row.setAttribute("style", "background-color: lightgray");
            clickedRow=row_index+1;
        }
    });

    let clicked=0;
    let clickedRow=-1;

    document.getElementById("editRole").value="STUDENT";
    document.getElementById("addRole").value="STUDENT";

    function setUserTime(){
        if(clickedRow!=-1){
            let row=document.getElementById("row"+clickedRow);
            let id=row.childNodes[3].innerHTML;
            document.getElementById('setTimeUserID').setAttribute("value",id);
            clickedRow=-1;
            clicked=0;
            document.getElementById('setUserTime').submit();
        }
        else{
            alert("Choose The Row");
        }
    }

    function getUserTime(){
        if(clickedRow!=-1){
            let row=document.getElementById("row"+clickedRow);
            let id=row.childNodes[3].innerHTML;
            document.getElementById('getUserTimeUserID').setAttribute("value",id);
            clickedRow=-1;
            clicked=0;
            document.getElementById('getUserTimeByID').submit();
        }
        else{
            alert("Choose The Row");
        }
    }

    function deleteUser(){
        if(clickedRow!=-1){
            let row=document.getElementById("row"+clickedRow);
            let id=row.childNodes[3].innerHTML;
            document.getElementById('userID').setAttribute("value",id);
            clickedRow=-1;
            clicked=0;
            document.getElementById('deleteForm').submit();
        }
        else{
            alert("Choose The Row");
        }
    }

    function toExcel(){
        document.getElementById('toExcel').submit();
    }

    function toWord(){
        if(clickedRow!=-1){
            let row=document.getElementById("row"+clickedRow);
            let id=row.childNodes[3].innerHTML;
            clickedRow=-1;
            clicked=0;
            document.getElementById('wordUserID').setAttribute("value",id);
            document.getElementById('toWord').submit();
        }
        else{
            alert("Choose The Row");
        }
    }

    function toPDF(){
        if(clickedRow!=-1){
            let row=document.getElementById("row"+clickedRow);
            let id=row.childNodes[3].innerHTML;
            clickedRow=-1;
            clicked=0;
            document.getElementById('pdfUserID').setAttribute("value",id);
            document.getElementById('toPDF').submit();
        }
        else{
            alert("Choose The Row");
        }
    }



    <%@include file="../js/addUserFormScript.js"%>
    <%@include file="../js/editUserFormScript.js"%>

    function downloadImage(){
        if(clickedRow!=-1) {
            let row = document.getElementById("row" + clickedRow);
            let path = row.childNodes[5].childNodes[0].getAttribute("src");
            document.getElementById('imagePath').setAttribute("href", path);
            location.reload();
        }
        else{
            alert("Choose the Row");
        }
    }

    function downloadDocument(){
        if(clickedRow!=-1) {
            let row = document.getElementById("row" + clickedRow);
            let path = row.childNodes[19].innerHTML;
            document.getElementById('documentPath').setAttribute("href", path);
            location.reload();
        }else
        {
            alert("Choose the Row!")
        }
    }
</script>

</div>
</div>

</body>
</html>