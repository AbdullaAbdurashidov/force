<%@include file="header.jsp"%>
<style>
    <%@include file="../../resource/css/styles.css"%>
</style>
<title>Web Site</title>
</head>

<body>
<div class="container">
    <div class="container">
        <h1 class="display-2">Sign In</h1>
        <form:form modelAttribute="user"  action="" method="post">
            <div class="mb-3">
                <label for="userName" class="form-label">User Name:</label>
                <form:input type="text" class="form-control" id="userName" name="userName" placeholder="enter your username" path="userName"/>
            </div>
            <div class="mb-3">
                <label for="userPassword" class="form-label">Password:</label>
                <form:input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="enter your password" path="password"/>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3">Sign In</button>
            </div>
            <span>Do you want to <a href="signUp.jsp">Sign Up</a>?</span>
        </form:form>    
    </div>
</div>


</body>
</html>