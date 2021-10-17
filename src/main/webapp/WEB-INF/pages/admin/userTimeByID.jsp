<%@ include file="../header.jsp"%>


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
<p class="text-center text-danger">${user.firstName} ${user.lastName}</p>

<div class="container ">
    <div class="btn-group" >
    </div>
</div>

<div style="position: absolute; top: 5px;right: 2%">
    <form id="setUserTime" action="/users/setTime" method="post" style="display: inline-block">
        <input type="text" name="userID" id="setTimeUserID" hidden>
        <input type="text" name="role" value="userTime"  hidden>
        <label for="datePicker">Date</label>
        <input type="date" name="datePicker" class="form-control" id="datePicker" style="width: 28%;display: inline;">
        <label for="timepickerArrival">Arrived at</label>
        <input type="time" id="timepickerArrival" name="timepickerArrival" class="form-control timepicker" style="width: 20%;display: inline;">
        <label for="timepickerLeaving">Left at</label>
        <input type="time" id="timepickerLeaving" name="timepickerLeaving" class="form-control timepicker" style="width: 20%;display: inline;">
    </form>
        <button class="btn btn-info" onclick="httpGet()" style="display: inline">Set</button>
</div>




<div class="row">
    <div class="col-md-1">

    </div>
    <div class="col-md-10">
        <table class="table table-hover" id="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Date</th>
                <th scope="col">Arrival Date</th>
                <th scope="col">Leaving Date</th>
            </tr>
            </thead>
            <tbody>
            <%int counter=1; %>
            <c:forEach items="${userTimes}" var="userTime">
                <tr id="row<%=counter%>">
                    <th scope="row"><%= counter %></th>
                    <td scope="row">${userTime.date}</td>
                    <td scope="row">${userTime.arrivalTime}</td>
                    <td scope="row">${userTime.leavingTime}</td>
                </tr>
                <% counter++; %>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-1">

    </div>
</div>

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



    function setUserTime(){
            document.getElementById('setTimeUserID').value=${user.userID};
            document.getElementById('setUserTime').submit();
    }

    function httpGet()
    {
        let arrival=document.getElementById('timepickerArrival').value;
        let leaving=document.getElementById('timepickerLeaving').value;
        let date=document.getElementById('datePicker').value;
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", "/users/setTime" , true );
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        xmlHttp.send("userID=${user.userID}&timepickerArrival="+arrival+"&timepickerLeaving="+leaving+"&datePicker="+date+"&role=role");
        setTimeout(reloadPage,500);
    }

    function reloadPage(){
        window.location.reload();
    }
</script>

</body>
</html>