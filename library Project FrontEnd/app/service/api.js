function fetchBooks() {
	fetch((Response) => Response, JSON())
		.then((data) => {
			state.books = data;
			renderBookList(data);
		})
		.catch((error) => {
			console.error("Error fetching books:", error);
		});
}
