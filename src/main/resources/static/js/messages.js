document.getElementsByName("naam")[0].oninvalid = function() {
    this.setCustomValidity("Verplicht");}
document.getElementsByName("boodschap")[0].oninvalid = function() {
    this.setCustomValidity("Verplicht");}