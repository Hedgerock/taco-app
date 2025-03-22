const parent = document.querySelector(".form-fields");
const fields = parent.querySelectorAll(".form-fields-label");

const defaultClassName = "form-fields-label__field-name";
const activeClassName = defaultClassName + "_active";

fields.forEach((field) => {
    const span = field.querySelector(".form-fields-label__field-name");
    const input = field.querySelector(".form-fields__current-input");

    if (input.value.length >= 1 && !span.className.includes(activeClassName)) {
        span.className += ` ${ activeClassName }`;
    }

    input.onfocus = () => {
        if (!span.className.includes(activeClassName)) {
            span.className += ` ${ activeClassName }`;
        }
    }

    input.onblur = (e) => {
        if (e.target.value.length < 1 && span.className.includes(activeClassName)) {
            span.className = defaultClassName;
        }
    }

    input.oninput = (e) => {
        const length = e.target.value.length;

        if (length >= 1 && !span.className.includes(activeClassName)) {
            span.className += ` ${ activeClassName }`;
        }

    }
})