<div id="editUserPane">
    <h1 class="h1 text-center" >Edit User</h1>
    <form  class="form-container" id="editUserForm" enctype="multipart/form-data" action="/users/edit"  method="POST">
        <input type="text" name="userID" id="editUserID" hidden>
        <div class="form-group">
            <label for="editFirstName" class="control-label"><b>First name</b></label>
            <input type="text" class="form-control" placeholder="" id="editFirstName" name="firstName" required>
        </div>

        <div class="form-group">
            <label for="editLastName"><b>Last name</b></label>
            <input type="text" class="form-control" placeholder="" id="editLastName" name="lastName" required>
        </div>

        <div class="form-group">
            <label for="editUserName"><b>User name</b></label>
            <input type="text" class="form-control" placeholder="" id="editUserName" name="userName" required>
        </div>

        <div class="form-group">
            <label for="editPassword"><b>Password</b></label>
            <input type="text" class="form-control" placeholder="" id="editPassword" name="password" required>
        </div>

        <div class="form-group">
            <label for="editBlocked"><b>Blocked</b></label>
            <input type="text" class="form-control" placeholder="" id="editBlocked" name="blocked" required>
        </div>
        <div class="form-group">
            <label>Insert your Image</label>
            <input type="file" class="form-control" id="imagePath" value="" name="imagePath" accept="image/jpeg, image/png" />
            <label>Insert your Document</label>
            <input type="file" class="form-control" id="documentPath" value="" name="documentPath" accept=".docx, .docx, .pdf"/>
        </div>
        <div class="form-group" hidden>
            <input type="text" name="role" id="editRole" >
            <input type="text" class="form-control" id="imagePath1" value="" name="imagePath1" >
            <input type="text" class="form-control" id="documentPath1" value="" name="documentPath1" >
        </div>
    </form>
    <button type="submit"  class="btn btn-success btn-lg" onclick="editUser()">Edit</button>
    <button class="btn btn-danger btn-lg" onclick="closeEditUserForm()">Cancel</button>
</div>