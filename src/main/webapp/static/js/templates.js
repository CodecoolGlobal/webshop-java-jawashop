export let template = {
    forIndexPageProduct: function(product) {
        const rootNode = document.createElement("div");
        rootNode.classList.add("col", "col-sm-12", "col-md-6", "col-lg-4");

            const cardNode = document.createElement("div");
            cardNode.classList.add("card", "card-container");

                const imageNode = document.createElement("img");
                imageNode.setAttribute("src", `/static/img/uploads/${product.id}.jpg`);
                imageNode.setAttribute("alt", "Image of the product.");

                const cardHeaderNode = document.createElement("div");
                cardHeaderNode.classList.add("card-header");

                    const cardTitleNode = document.createElement("h4");
                    cardTitleNode.classList.add("card-title");
                    cardTitleNode.innerHTML = product.name;

                    const cardDescriptionNode = document.createElement("p");
                    cardDescriptionNode.classList.add("card-text");
                    cardDescriptionNode.innerHTML = product.description;

                cardHeaderNode.appendChild(cardTitleNode);
                cardHeaderNode.appendChild(cardDescriptionNode);

                const cardBodyNode = document.createElement("div");
                cardBodyNode.classList.add("card-body");

                    const productPriceContainerNode = document.createElement("div")
                    productPriceContainerNode.classList.add("card-text");

                        const productPriceNode = document.createElement("p");
                        productPriceNode.classList.add("lead");
                        productPriceNode.innerHTML = product.price;

                    productPriceContainerNode.appendChild(productPriceNode);

                    const addToCartContainerNode = document.createElement("div")
                    productPriceContainerNode.classList.add("card-text");

                        const addToCartNode = document.createElement("a");
                        addToCartNode.classList.add("btn", "btn-success");
                        addToCartNode.setAttribute("src", "#");
                        addToCartNode.innerHTML = "Add to cart";

                    addToCartContainerNode.appendChild(addToCartNode);

                cardBodyNode.appendChild(productPriceContainerNode);
                cardBodyNode.appendChild(addToCartContainerNode);

            cardNode.appendChild(imageNode);
            cardNode.appendChild(cardHeaderNode);
            cardNode.appendChild(cardBodyNode);

        rootNode.appendChild(cardNode);

        return rootNode;
    }
}
