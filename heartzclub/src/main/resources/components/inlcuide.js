async function loadComponent(id, file) {

    const response = await fetch(file);

    const content = await response.text();

    document.getElementById(id).innerHTML =
        content;

    // HEADER
    if (id === "header") {

        const script =
            document.createElement("script");

        script.src =
            "/heartzclub/src/main/resources/components/headerScript.js";

        document.body.appendChild(script);
    }
}

// HEADER
loadComponent(
    "header",
    "/heartzclub/src/main/resources/components/header.html"
);

// FOOTER
loadComponent(
    "footer",
    "/heartzclub/src/main/resources/components/footer.html"
);