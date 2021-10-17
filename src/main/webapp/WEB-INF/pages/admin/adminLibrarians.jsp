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

<title>Administrator Page | Librarians</title>
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
                    <li><a href="/admin/students">Students</a></li>
                    <li class="active"><a href="/admin/librarians">Librarians</a></li>
                    <li><a href="/admin/administrators">Administrators</a></li>
                    <li><a href="/admin/books">Books</a></li>
                    <li><a href="#"><button class="btn btn-success btn-xs" onclick="openAddUserForm()">Add User</button></a></li>
                    <li><a href="#"><button class="btn btn-info btn-xs" onclick="openEditUserForm()">Edit User</button></a></li>
                    <li><a href="#"><button class="btn btn-danger btn-xs" onclick="deleteUser()" >Delete User</button></a></li>
                    <li><a id="imagePath" href="" download><button type="button" onclick="downloadImage()" class="btn btn-primary btn-xs" >Download Image</button></a></li>
                    <li><a id="documentPath" href="" download><button type="button" onclick="downloadDocument()" class="btn btn-primary btn-xs" >Download Document</button></a></li>
                    <li><a href="#">
                        <form action="/users/excel/download" method="get">
                            <input type="text" name="role" value="LIBRARIAN" hidden>
                            <button type="submit" class="btn btn-primary btn-xs">To Excel</button>
                        </form>
                    </a></li>
                </ul>
            </div>
        </div>
    </nav>
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


<div id="none">
</div>

<%@include file="../forms/editUserForm.jsp"%>
<%@include file="../forms/addUserForm.jsp"%>

<form action="/users/delete" id="deleteForm" method="post">
    <input type="text" value="" name="userID" id="userID" hidden>
    <input type="text" value="LIBRARIAN" name="role" id="role" hidden>
</form>

<script>
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

    document.getElementById("editRole").value="LIBRARIAN";
    document.getElementById("addRole").value="LIBRARIAN";

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

    <%@include file="../js/addUserFormScript.js"%>
    <%@include file="../js/editUserFormScript.js"%>

    function downloadImage(){
        if(clickedRow!=-1) {
            let row = document.getElementById("row" + clickedRow);
            let path = row.childNodes[5].childNodes[0].getAttribute("src");
            alert(path);
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
            alert(path);
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