document.getElementById('brand-check').addEventListener('change', function() {
    var isChecked = this.checked;
    document.getElementById('brand-input').disabled = !isChecked;

    if (!isChecked) {
        document.getElementById('brand-input').value = "";
    }
});

document.getElementById('weight-check').addEventListener('change', function() {
    var isChecked = this.checked;
    document.getElementById('weight-input').disabled = !isChecked;

    if (!isChecked) {
        document.getElementById('weight-input').value = "";
    }
});

document.getElementById('dimensions-check').addEventListener('change', function() {
    var isChecked = this.checked;
    document.getElementById('length-input').disabled = !isChecked;
    document.getElementById('width-input').disabled = !isChecked;
    document.getElementById('height-input').disabled = !isChecked;

    if (!isChecked) {
        document.getElementById('length-input').value = "";
        document.getElementById('width-input').value = "";
        document.getElementById('height-input').value = "";
    }
});