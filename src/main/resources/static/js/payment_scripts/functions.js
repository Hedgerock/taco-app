import {expDateRegexPattern} from "./regex.js";

export function validatePattern(e) {
    const validateFormat = expDateRegexPattern;
    const parent = e.target.parentElement;

    let error = parent.querySelector(".error-message");

    if (!validateFormat.test(e.target.value)) {

        if (!error) {
            initError(parent, "Invalid format");
        }

    } else if (error) {
        error.remove();
    }
}

export function initError(parent, message) {
    const error = document.createElement("span");
    error.className = "error-message";
    error.textContent = message;

    parent.append(error);
}

export function inputOperations(e, SIZE, regex, multiple, delimiter) {

    const value = simpleInputOperations(e, SIZE, regex);

    if (!delimiter && !multiple) return;

    const expRes = multiple - 1;

    if (value.length % multiple === expRes && value.length < SIZE) {
        e.target.value += delimiter;
    }
}

export function simpleInputOperations(e, SIZE, regex) {
    let value = e.target.value;
    e.target.value = value.replace(regex, "");

    if (value.length > SIZE) {
        e.target.value = value.slice(0, SIZE);
    }

    return value;
}

export function removeValue(ev, delimiter) {
    const value = ev.target.value;
    if (ev.key === "Backspace" && value[value.length - 1] === delimiter) {
        ev.target.value = value.slice(0, -1);
    }
}

export function validateYear(e) {
    if (!e.target.value) return;

    const [_, month, year] = e.target.value.match(expDateRegexPattern);
    const curYear = new Date().getFullYear() % 100;

    if (parseInt(year, 10) < curYear) {
        initError(e.target.parentElement, `Can't be earlier than ${ curYear }`);
    }
}

export function initYearValidation(e, parent) {
    const errorAfterPattern = parent.querySelector(".error-message");
    if (!errorAfterPattern) {
        validateYear(e);
    }
}