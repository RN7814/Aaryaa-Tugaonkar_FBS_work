// One place for requests, session tokens, and useful error messages.
let csrfToken = "";
export async function request(path, method = "GET", body) {
  const headers = {};
  if (method !== "GET") { headers["X-CSRF-Token"] = csrfToken; }
  if (body !== undefined) { headers["Content-Type"] = "application/json"; }
  let response;
  try {
    response = await fetch("/api" + path, { method, headers, credentials: "same-origin", body: body === undefined ? undefined : JSON.stringify(body) });
  } catch {
    throw new Error("The app is unavailable. Check that the local server is running.");
  }
  const data = await response.json().catch(() => ({ message: "The server could not complete this request." }));
  if (data.csrfToken) { csrfToken = data.csrfToken; }
  if (!response.ok) {
    if (response.status === 401 && !path.startsWith("/auth/")) { location.replace("/login.html"); }
    if (response.status === 403 && data.message?.includes("form session expired")) { await session(); }
    throw new Error(data.message || "Please try again.");
  }
  return data;
}
export function session() { return request("/auth/session"); }
