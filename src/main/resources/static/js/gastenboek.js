"use strict"

const gastenboekForm = document.querySelector("form");

document.getElementById("knop").onclick = function () {
    gastenboekForm.classList.remove("verborgen");
    document.getElementById("knop").className = "verborgen";
}

gastenboekForm.onsubmit = formSubmitten;

function formSubmitten(){
    const fouteInputs = document.querySelectorAll("input:invalid");
    if (fouteInputs.length === 0) {
        document.querySelector("form").classList.add("verborgen");
        document.getElementById("knop").classList.remove("verborgen");
    }
}