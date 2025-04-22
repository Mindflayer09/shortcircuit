import { useState } from 'react';
import axios from 'axios';

function UsernameInputPage() {
  const [username, setUsername] = useState('');
  const backend = import.meta.env.VITE_BACKEND_URL;
 
  const handleSubmit = () => {
    console.log("Username submitted:", username);
    axios.post(`${backend}/createUser`, { username } )
      .then(response => {
        console.log("Login successful:", response.data);
        localStorage.setItem("id", response.data); // Store the token in local storage
        window.location.reload(); // Reload the page to show the main app
      })
      .catch(error => {
        console.error("Error during login:", error);
      });
  };

  return (
    <div className="min-h-screen bg-black text-white relative">
      {/* Circuit Lines */}
      <div className="absolute inset-0 pointer-events-none overflow-hidden hidden md:block">
        {/* Top right circuit */}
        <div className="absolute top-16 right-0">
          <svg width="500" height="500" viewBox="0 0 500 500">
            <path d="M500,100 L350,100 L350,250 L250,250" stroke="#2DD4BF" strokeWidth="1" fill="none" />
            <circle cx="250" cy="250" r="4" fill="#2DD4BF" />
          </svg>
        </div>
        
        {/* Bottom left circuit */}
        <div className="absolute bottom-0 left-80">
          <svg width="300" height="400" viewBox="0 0 300 400">
            <path d="M0,0 L0,200 L150,200" stroke="#2DD4BF" strokeWidth="1" fill="none" />
            <circle cx="150" cy="200" r="4" fill="#2DD4BF" />
          </svg>
        </div>
        
        {/* Middle right circuit */}
        <div className="absolute top-1/2 right-0">
          <svg width="400" height="300" viewBox="0 0 400 300">
            <path d="M400,150 L200,150 L200,300" stroke="#2DD4BF" strokeWidth="1" fill="none" />
            <circle cx="200" cy="300" r="4" fill="#2DD4BF" />
          </svg>
        </div>
      </div>

      <div className="flex flex-col h-screen border border-teal-400/30 rounded-lg m-4 overflow-hidden z-10 relative">
        {/* Header with brand */}
        <div className="flex border-b border-teal-400/30">
          <div className="w-full p-4 md:p-6 flex items-center">
            <div className="text-teal-400 mr-2 md:mr-4">
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6 md:w-8 md:h-8">
                <path strokeLinecap="round" strokeLinejoin="round" d="M3.75 13.5l10.5-11.25L12 10.5h8.25L9.75 21.75 12 13.5H3.75z" />
              </svg>
            </div>
            <h1 className="text-xl md:text-3xl font-bold">Short-Circuit</h1>
          </div>
        </div>

        {/* Main content - centered username form */}
        <div className="flex-1 flex items-center justify-center p-4 md:p-6">
          <div className="w-full max-w-md border border-teal-400/30 rounded-lg p-6 md:p-8 bg-black/50 backdrop-blur">
            <h2 className="text-xl md:text-2xl font-bold mb-6 text-center">Welcome to Short-Circuit</h2>
            
            <div className="mb-8 text-center text-gray-400">
              Enter your username to get started with URL shortening
            </div>
            
            <div className="space-y-6">
              <div>
                <label htmlFor="username" className="block text-sm font-medium mb-2">Username</label>
                <input
                  type="text"
                  id="username"
                  placeholder="Enter your username"
                  className="w-full bg-transparent border border-teal-400/30 rounded-lg p-3 text-white focus:outline-none focus:ring-2 focus:ring-teal-400/50"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
              </div>
              
              <button
                onClick={handleSubmit}
                className={`w-full bg-teal-400 text-black font-medium py-3 rounded-lg transition-colors ${
                  username.trim() ? 'hover:bg-teal-300' : 'opacity-50 cursor-not-allowed'
                }`}
                disabled={!username.trim()}
              >
                Continue
              </button>
             
            </div>
          </div>
        </div>
        
        {/* Footer */}
        <div className="border-t border-teal-400/30 p-4 text-center text-sm text-gray-500">
          © {new Date().getFullYear()} Short-Circuit • All rights reserved
        </div>
      </div>
    </div>
  );
}

export default UsernameInputPage;