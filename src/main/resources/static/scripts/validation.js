var radio = document.querySelectorAll('input[type="radio"]');
var valid = document.querySelector('input[type="submit"]');
var reset = document.querySelector('input[type="reset"]');

document.body.addEventListener('change', () => {
    for(var i = 0; i < radio.length; i += 5) {
        for(var j = 0; j < 5; j++) {
            if(!radio[i+j].checked) {
                valid.disabled = true;
            } else {
                valid.disabled = false;
                break;
            }
        }
        if(valid.disabled) {
            return;
        } 
    }
    document.getElementById('submit-span').style.opacity = 0;
});

reset.addEventListener('click', () => {
    valid.disabled = true;
    document.getElementById('submit-span').style.opacity = 1;
});

