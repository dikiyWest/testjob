package kz.uco.ruslan.testjob.screen.product;

import io.jmix.ui.screen.*;
import kz.uco.ruslan.testjob.entity.Product;

@UiController("Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
public class ProductEdit extends StandardEditor<Product> {
}