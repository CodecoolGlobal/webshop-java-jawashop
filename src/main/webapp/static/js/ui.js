export function getViewRoot() {
    return document.querySelector("#content-container");
}

export function exportFormInputs(formNode) {
    const inputs = Array.from(formNode.querySelectorAll("input"));
    const reducer = (accumulatorObject, inputNode) => {
        const key = inputNode.getAttribute("name");
        accumulatorObject[key] = inputNode.value;
        return accumulatorObject;
    };
    return inputs.reduce(reducer, {});
}
