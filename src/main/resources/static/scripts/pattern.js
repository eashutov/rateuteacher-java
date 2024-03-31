var codeButton = document.getElementById('btn');
var input = document.getElementById('code');
const re = new RegExp('^[A-Za-z0-9]{6}$');

codeButton.disabled = true;

input.addEventListener('input', () => {
    if(re.test(input.value)) {
        codeButton.disabled = false;
    } else {
        codeButton.disabled = true;
    }
});
