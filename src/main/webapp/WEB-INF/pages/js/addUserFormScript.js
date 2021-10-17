function openAddUserForm() {
    document.getElementById("body").setAttribute("style","filter: blur(5px)");
    document.getElementById("addUserPane").setAttribute("style"," display: block;");
    document.getElementById("none").setAttribute("style", "display: block;");
}
function closeAddUserForm() {
    document.getElementById("body").removeAttribute("style");
    document.getElementById("addUserPane").setAttribute("style","display: none");
    document.getElementById("none").setAttribute("style", "display: none;");
}