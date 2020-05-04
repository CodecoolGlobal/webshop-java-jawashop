export let template = {
    forNavbar: function() {
        return `
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
                <a class="navbar-brand" href="#">
                    <img class="logo" src="../static/img/logo.svg" alt="">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown 1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Categories
                            </a>
                            <div class="dropdown-menu" id="category-dropdown" aria-labelledby="navbarDropdown"></div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown 2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Suppliers
                            </a>
                            <div class="dropdown-menu" id="supplier-dropdown" aria-labelledby="navbarDropdown"></div>
                        </li>
                        <li class="nav-item d-none">
                            <a id="loginBtn" href="javascript:" class="nav-link">Login</a>
                        </li>
                        <li class="nav-item d-none">
                            <a id="registrationBtn" href="javascript:" class="nav-link">Sign up</a>
                        </li>
                        <li class="nav-item d-none">
                            <a id="logoutBtn" href="javascript:" class="nav-link">Logout</a>
                        </li>
                    </ul>
                    <div class="row d-none">
                        <div id="cart-button" class="cart col-auto">
                            <div id="cart-item-counter" class="d-none"></div>
                            <a>
                                <i class="fas fa-shopping-cart"></i>
                            </a>
                        </div>
                        <div id="cart-item-total-value" class="col-auto d-none ml-1 text-light"></div>
                        <div id="cart-item-total-value-mobile" class="col-auto d-block d-lg-none h6 text-light"></div>
                    </div>
                </div>
            </nav>
        `
    }
}