"use strict"

const gastenboekForm = document.querySelector("form");

document.getElementById("knop").onclick = function () {
    gastenboekForm.classList.remove("verborgen");
    document.getElementById("knop").className = "verborgen";
}

gastenboekForm.onsubmit = formSubmitten;

function formSubmitten(){
    const fouteInputs = document.querySelectorAll("input:invalid");
    for (const input of fouteInputs){
        input.setCustomValidity("Verplicht");
    }
    /*if (fouteInputs.length === 0) {
        document.querySelector("form").classname = "verborgen";
        document.getElementById("knop").classList.remove("verborgen");
    }*/
}