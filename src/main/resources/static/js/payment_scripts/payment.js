import {
    initYearValidation,
    inputOperations,
    removeValue,
    simpleInputOperations,
    validatePattern
} from "./functions.js";
import {CC_CVV_SIZE, CC_SIZE, EXP_DATE_SIZE} from "./constants.js";
import {ccRegex, cvvRegex, expDateRegex} from "./regex.js";

const ccCard = document.querySelector(".form-fields__current-input_credit-card");
const ccExpiration = document.querySelector(".form-fields__current-input_expiration-date");
const ccCvv = document.querySelector(".form-fields__current-input_cvv");

ccCard.oninput = (e) => inputOperations(e, CC_SIZE, ccRegex, 5, "-");

ccExpiration.oninput = (e) => {
    const parent = e.target.parentElement;
    const error = parent.querySelector(".error-message");
    const length = e.target.value.length;

    if (length < EXP_DATE_SIZE && error) {
        error.remove();
    }

    if (length === EXP_DATE_SIZE) {
        validatePattern(e);
        initYearValidation(e, parent);
    }

    inputOperations(e, EXP_DATE_SIZE, expDateRegex, 3, "/")
};

ccExpiration.onblur = (e) => {
    const length = e.target.value.length;
    const parent = e.target.parentElement;

    if (length >= 1) {
        validatePattern(e);
    }

    if (length === EXP_DATE_SIZE) {
        initYearValidation(e, parent);
    }
}

ccCvv.oninput = (e) => simpleInputOperations(e, CC_CVV_SIZE, cvvRegex);


ccCard.addEventListener("keydown", (ev) => removeValue(ev, "-"));
ccExpiration.addEventListener("keydown", (ev) => removeValue(ev, "/"));
