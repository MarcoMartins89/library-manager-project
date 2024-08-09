import * as routes from "./routes";

function route() {
	const windowHash = window.location.hash;

	let route = Object.values(routes).find(({ hash }) =>
		windowHash.startsWith(hash)
	);

	if (!route) {
		route = route.main;
		window.location.hash;
	}

	route.init();
}

window.onhashchange = route;
route();
