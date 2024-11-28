const productId = document.getElementById('id');

document.getElementById('get-product').addEventListener("click", async() => {
    // Log the product ID value
    console.log("Product ID entered:", productId.value);

    try{
        const response = await fetch(`http://localhost:8080/product/${productId.value}`);
        if(response.ok){
            response.json().then((data)=>{
                document.getElementById('name').value = data.name || "";
                document.getElementById('price').value = data.price || 0;
                document.getElementById('size').value = data.size || "";
                document.getElementById('color').value = data.color || "";
            })
        }
        if(response.status == 404){
            document.getElementById('message').innerText = "NOT FOUND";
            setTimeout(()=>{
                productId.value = 0;
                document.getElementById('message').innerText = "";
            },3000);
        }
    }catch(err){
        console.log("Error : ",err);
    }
});

document.getElementById('update-form').addEventListener("submit", async (event) => {
    event.preventDefault(); // Prevent the default form submission

    const id = productId.value;
    const name = document.getElementById('name').value;
    const price = document.getElementById('price').value;
    const size = document.getElementById('size').value;
    const color = document.getElementById('color').value;
    try {
        const response = await fetch(`http://localhost:8080/update-product/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, price, size, color })
        });
        console.log("RESPONSE",response);
        if (response.ok) {
            document.getElementById('message').innerText = "Product updated successfully!";
        } else if (response.status === 404) {
            document.getElementById('message').innerText = "Product not found!";
        }
    } catch (err) {
        console.log("Error:", err);
    }
});
