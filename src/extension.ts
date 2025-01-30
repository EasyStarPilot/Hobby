


import * as vscode from 'vscode';
import { Ollama } from 'ollama'; // Ensure this matches how Ollama exports its functionalities

export function activate(context: vscode.ExtensionContext) {
    const disposable = vscode.commands.registerCommand('CC-DeepSeek-ext.Deep', () => {
        // Create a new webview panel
        const panel = vscode.window.createWebviewPanel(
            'deepChat',                // Identifies the webview
            'Deep Seek Chat',          // Title of the panel
            vscode.ViewColumn.One,      // Where it will open
            { enableScripts: true }    // Enable JavaScript inside the webview
        );

        // Set the webview's content
        panel.webview.html = getWebviewContent();

        // Initialize Ollama client
        const ollama = new Ollama(); // Adjust initialization based on Ollama's documentation

        // Handle messages from the webview
        panel.webview.onDidReceiveMessage(
            async (message: any) => {
                if (message.command === 'chat') {
                    const userPrompt = message.text;
                    let responseText = '';

                    try {
                        const streamResponse = await ollama.chat({
                            model: 'deepseek-r1:8b',
                            messages: [{ role: 'user', content: userPrompt }],
                            stream: true,
                        });

                        for await (const segment of streamResponse) {
                            if (segment.message && segment.message.content) {
                                responseText += segment.message.content;
                                // Send the accumulated response to the webview
                                panel.webview.postMessage({ command: 'chatResponse', text: responseText });
                            }
                        }
                    } catch (error) {
                        const errorMessage = (error instanceof Error) ? error.message : String(error);
                        panel.webview.postMessage({ command: 'chatResponse', text: 'Error: ' + errorMessage });
                    }
                }
            },
            undefined,
            context.subscriptions
        );

        // Add the panel to subscriptions to manage its lifecycle
        context.subscriptions.push(panel);
    });

    // Register the disposable command
    context.subscriptions.push(disposable);
}

export function deactivate() {}

// Function to return the HTML content of the webview
function getWebviewContent() {
    return /*HTML*/`
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Deep Seek Chat</title>
            <style>
                body { font-family: Arial, sans-serif; padding: 20px; }
                h1 { color: #007acc; }
                #chatbox { border: 1px solid #ddd; padding: 10px; height: 300px; overflow-y: auto; margin-bottom: 10px; }
                textarea { width: 100%; padding: 8px; margin-top: 10px; resize: vertical; }
                button { padding: 8px 16px; margin-top: 10px; }
                .message { margin: 5px 0; }
                .user { color: #007acc; }
                .deepseek { color:rgb(97, 175, 212); }
            </style>
        </head>
        <body>
            <h1>Deep Seek Chat</h1>
            <div id="chatbox">
                <!-- Chat messages will appear here -->
            </div>
            <textarea id="prompt" rows="3" placeholder="Type a message..."></textarea>
            <button id="askBtn">Send</button>

            <script>
                const vscode = acquireVsCodeApi();

                document.getElementById('askBtn').addEventListener('click', () => {
                    const text = document.getElementById('prompt').value.trim();
                    if (text) {
                        appendMessage('You', text);
                        vscode.postMessage({ command: 'chat', text });
                        document.getElementById('prompt').value = '';
                    }
                });

                // Listen for messages from the extension
                window.addEventListener('message', event => {
                    const { command, text } = event.data;
                    if (command === 'chatResponse') {
                        updateDeepSeekMessage(text);
                    }
                });

                // Function to append user messages to the chatbox
                function appendMessage(sender, message) {
                    const chatbox = document.getElementById('chatbox');
                    const messageElement = document.createElement('p');
                    messageElement.className = 'message ' + (sender.toLowerCase());
                    messageElement.innerHTML = '<strong>' + sender + ':</strong> ' + message;
                    chatbox.appendChild(messageElement);
                    chatbox.scrollTop = chatbox.scrollHeight;
                }

                // Function to update DeepSeek's message
                function updateDeepSeekMessage(message) {
                    let deepSeekElement = document.getElementById('deepseek-message');
                    if (!deepSeekElement) {
                        deepSeekElement = document.createElement('p');
                        deepSeekElement.id = 'deepseek-message';
                        deepSeekElement.className = 'message deepseek';
                        deepSeekElement.innerHTML = '<strong>DeepSeek:</strong> ';
                        document.getElementById('chatbox').appendChild(deepSeekElement);
                    }
                    // Update the content
                    deepSeekElement.innerHTML = '<strong>DeepSeek:</strong> ' + message;
                    document.getElementById('chatbox').scrollTop = document.getElementById('chatbox').scrollHeight;
                }
            </script>
        </body>
        </html>
    `;
}