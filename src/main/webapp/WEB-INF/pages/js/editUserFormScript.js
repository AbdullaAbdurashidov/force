function openEditUserForm(){
    if(clickedRow!=-1){
        let row=document.getElementById("row"+clickedRow);
        //Adding data to input fields
        let id=row.childNodes[3].innerHTML;
        let firstName=row.childNodes[7].innerHTML;
        let lastName=row.childNodes[9].innerHTML;
        let userName=row.childNodes[11].innerHTML;
        let password=row.childNodes[13].innerHTML;
        let role=row.childNodes[15].innerHTML;
        let blocked=row.childNodes[17].innerHTML;
        let imagePath=row.childNodes[5].childNodes[0].getAttribute("src");
        let documentPath=row.childNodes[19].innerHTML;


        document.getElementById("editUserID").value=id;
        document.getElementById("editFirstName").value=firstName;
        document.getElementById("editLastName").value=lastName;
        document.getElementById("editUserName").value=userName;
        document.getElementById("editPassword").value=password;
        document.getElementById("editRole").value=role;
        document.getElementById("editBlocked").value=blocked;
        document.getElementById('imagePath1').value=imagePath;
        document.getElementById('documentPath1').value=documentPath;


        //Styling Process
        document.getElementById("body").setAttribute("style","filter: blur(5px)");
        document.getElementById("editUserPane").setAttribute("style"," display: block;");
        document.getElementById("none").setAttribute("style", "display: block;");
    }
    else{
        alert("Choose The Row");
    }
}
function closeEditUserForm(){
    document.getElementById("body").removeAttribute("style");
    document.getElementById("editUserPane").setAttribute("style","display: none");
    document.getElementById("none").setAttribute("style", "display: none;");
}
function editUser(){
    if(clickedRow!=-1){
        clickedRow=-1;
        clicked=0;
        document.getElementById('editUserForm').submit();
    }
    else{
        alert("Choose The Row");
    }
}