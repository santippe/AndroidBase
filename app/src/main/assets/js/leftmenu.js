let leftMenu = document.querySelector('#leftmenu')
let ogreMenu = document.querySelector('#ogremenu')
let startTouch = null
window.addEventListener("touchstart", (e) => {
    startTouch = e.touches[0].clientX
    //JsInterface.Toast(startTouch);          
});
window.addEventListener("touchmove", (e) => {
    endTouch = e.touches[0].clientX
    if (endTouch + 100 < startTouch) {
        leftMenu.style.left = "-200px";
        ogreMenu.style.opacity = '0'
        ogreMenu.style.width = '0px'
    }
    if (endTouch > startTouch + 100) {
        leftMenu.style.left = "0px";
        ogreMenu.style.opacity = '1'
        ogreMenu.style.width = '100%'
    }
    //JsInterface.Toast(endTouch-startTouch);    
});
function tryLogout(e) {
    JsInterface.StoreUserID('');
    location.assign('login.html')
    e.preventDefault();
}