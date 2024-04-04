document.addEventListener("DOMContentLoaded", () => {
    document.getElementById('modal').showModal();
    btn = document.getElementById('modal-btn');
    btn.disabled = true;
    var timeleft = 5;
    var downloadTimer = setInterval(() => {
        if(timeleft <= 0){
        clearInterval(downloadTimer);
        btn.innerHTML = "OK";
        btn.disabled = false;
        return;
        }
        btn.innerHTML = timeleft;
        timeleft -= 1;
    }, 1000);
});