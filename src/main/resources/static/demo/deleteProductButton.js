// Select all delete buttons
const deleteButtons = document.querySelectorAll("#delete-button");

deleteButtons.forEach((deleteButton) => {
    deleteButton.addEventListener("click", async (event) => {
        // Get the product ID from the data attribute
        const productId = event.target.closest("#delete-button").getAttribute("data-id");

        try {
            const response = await fetch(`/delete-product/${productId}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                console.log("Product deleted successfully.");
                // Optionally, remove the product from the UI
                event.target.closest(".list-container").remove();
            } else if (response.status === 404) {
                console.warn("Product not found.");
            } else {
                console.error("Failed to delete product.");
            }
        } catch (error) {
            console.error("Error:", error);
        }
    });
});
