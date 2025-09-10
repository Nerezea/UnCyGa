import { NavLink } from 'react-router-dom';

const Navigation = () => {
  const items = [{ name: 'Home', to: '/', id: 1 }];
  return (
    <nav>
      <ul>
        {items.map((item) => {
          return (
            <li key={item.id}>
              <NavLink
                to={item.to}
                style={({ isActive }) => ({
                  color: isActive ? 'black' : '',
                })}
              >
                {item.name}
              </NavLink>
            </li>
          );
        })}
      </ul>
      <i className="burgermenu">
        <ul>
          {items.map((item) => {
            return (
              <li key={item.id}>
                <NavLink
                  to={item.to}
                  style={({ isActive }) => ({
                    color: isActive ? 'black' : '',
                  })}
                >
                  {item.name}
                </NavLink>
              </li>
            );
          })}
        </ul>
      </i>
    </nav>
  );
};

export default Navigation;
