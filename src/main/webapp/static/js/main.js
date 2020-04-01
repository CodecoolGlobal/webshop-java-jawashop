import {RoutingManager} from './routing/manager.js';
import {ProductView} from './product/view.js';
import {SupplierView} from './supplier/view.js';

function init(domReadyEvent) {
    const routingManager = new RoutingManager(window.history);
    const productView = new ProductView(routingManager);
    const supplierView = new SupplierView(routingManager);
    routingManager.goTo('/products/all');
    routingManager.goTo('/suppliers/all');
}

window.addEventListener('DOMContentLoaded', init);
