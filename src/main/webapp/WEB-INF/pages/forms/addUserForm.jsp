<div class="form-popup" id="addUserPane">
    <h1 class="h1 text-center">Add User</h1>
    <form  class="form-container" id="addUserForm" enctype="multipart/form-data"  action="/users/add"  method="POST">
        <div class="form-group">
            <label for="addFirstName" class="control-label"><b>First name</b></label>
            <input type="text" class="form-control" placeholder="" id="addFirstName" name="firstName" required>
        </div>

        <div class="form-group">
            <label for="addLastName"><b>Last name</b></label>
            <input type="text" class="form-control" placeholder="" id="addLastName" name="lastName" required>
        </div>

        <div class="form-group">
            <label for="addUserName"><b>User name</b></label>
            <input type="text" class="form-control" placeholder="" id="addUserName" name="userName" required>
        </div>

        <div class="form-group">
            <label for="addPassword"><b>Password</b></label>
            <input type="text" class="form-control" placeholder="" id="addPassword" name="password" required>
        </div>
        <div class="form-group">
            <label>Insert your Image</label>
            <input type="file" class="form-control" name="imagePath" accept="image/jpeg, image/png" required/>
            <label>Insert your Document</label>
            <input type="file" class="form-control" name="documentPath" accept=".docx, .docx, .pdf" required/>
        </div>
            <input type="text" name="role" id="addRole" hidden>
        <button type="submit"  class="btn btn-success btn-lg">Add</button>
        <button type="submit" class="btn btn-danger btn-lg" onclick="closeAddUserForm()">Cancel</button>
    </form>
</div>