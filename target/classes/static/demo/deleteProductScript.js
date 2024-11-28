const deleteButton = document.getElementById('delete-button')

async function onDeleteButtonClick(){
        const productId = document.getElementById('product-id').value;
        if (!productId) {
            document.getElementById('message').innerText = 'Please enter a product ID.';
            window.alert("Please enter a product Id");
            return;
        }

        try {
            const response = await fetch(`/delete-product/${productId}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                document.getElementById('message').innerText = 'Product deleted successfully.';
                console.log('Product deleted successfully.', response.json());
            } else if (response.status === 404) {
                document.getElementById('message').innerText = 'Product not found.';
                console.warn('Product not found.');
            } else {
                document.getElementById('message').innerText = 'Failed to delete product.';
                console.error('Failed to delete product.');
            }
        } catch (error) {
            document.getElementById('message').innerText = 'Error: Unable to delete product.';
            console.error('Error:', error);
        }
}

deleteButton.addEventListener("click",onDeleteButtonClick)