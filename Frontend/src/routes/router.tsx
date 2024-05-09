import ChildPage from '@pages/ChildPage'
import DispatchPage from '@pages/DispatchPage'
import DriveHistoryPage from '@pages/DriveHistoryPage'
import HomePage from '@pages/HomePage'
import MyPage from '@pages/MyPage'
import OperatePage from '@pages/OperatePage'
import ParentViewPage from '@pages/ParentViewPage'
import SignInPage from '@pages/SignInPage'
import SignUpPage from '@pages/SignUpPage'
import StationPage from '@pages/StationPage'

import ProtectedRoute from '@routes/ProtectedRoute'
import App from 'App'
import { createBrowserRouter } from 'react-router-dom'

const router = createBrowserRouter([
  { path: '/parent-view', element: <ParentViewPage /> },
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: 'signup',
        element: <SignUpPage />,
      },
      {
        path: 'signin',
        element: <SignInPage />,
      },
      {
        element: <ProtectedRoute />,
        children: [
          {
            index: true,
            element: <HomePage />,
          },
          {
            path: 'home',
            element: <HomePage />,
          },
          {
            path: 'manage',
            children: [
              {
                path: 'child',
                element: <ChildPage />,
              },
              {
                path: 'dispatch',
                element: <DispatchPage />,
              },
              {
                path: 'dispatch/station',
                element: <StationPage />,
              },
              {
                path: 'operate',
                element: <OperatePage />,
              },
              {
                path: 'drive-history/:shuttleId',
                element: <DriveHistoryPage />,
              },
            ],
          },
          {
            path: 'mypage',
            element: <MyPage />,
          },
        ],
      },
    ],
  },
])

export default router
