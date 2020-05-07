export let template = {
    forNavbar: function() {
        return `
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top shadow-strong">
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
                    </ul>
                    <ul class="navbar-nav ml-auto">
                        <li id="profileMenu" class="nav-item dropdown d-none">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="far fa-user"></i> Profile
                            </a>
                            <div class="dropdown-menu" id="category-dropdown" aria-labelledby="navbarDropdown">
                                <span id="cart-button" class="dropdown-item">
                                    <div>
                                        Shopping Cart
                                        <span>(<span id="cart-item-counter" class="text-primary">0</span>)</span>
                                    </div>
                                </span>
                                <span id="orderHistoryBtn" class="dropdown-item">Order History</span>
                                <hr class="solid">
                                <span id="logoutBtn" href="javascript:" class="dropdown-item">Logout</span>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        `;
    }
}