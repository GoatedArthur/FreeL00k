# FreeL00k

A 360-degree freelook mod for Minecraft Forge 1.8.9. Hold the freelook key (Left Alt by default) to freely rotate your camera without changing your movement direction.

## Features

- Full 360-degree horizontal and vertical camera rotation
- Hold or toggle mode (configurable)
- Configurable max yaw (90 / 180 / 360 degrees)
- No pitch limit -- look straight up or down
- Choose default perspective: keep current, third-person back, or third-person front
- Config GUI accessible from the Forge mod list
- Invert mouse support
- Camera starts level (pitch = 0) facing your current direction
- Player model stays frozen -- no head or body rotation
- Proper camera positioning -- position syncs with your look direction for realistic perspective around corners and blocks

## Controls

- **Freelook (360):** Left Alt (default, rebindable in Controls > FreeL00k)

## Configuration

Access the config screen from the Forge mod list. Options:

| Option | Values |
|--------|--------|
| Perspective | Keep Current / Third Person (Back) / Third Person (Front) |
| Mode | Hold (hold key) / Toggle (press to toggle) |
| Max Yaw | 90 / 180 / 360 degrees |

## Installation

1. Install Minecraft Forge 1.8.9
2. Download the `.jar` from the Releases section
3. Place it in your `.minecraft/mods` folder
4. Launch the game and bind the freelook key in Controls if desired

## Building from source

```
gradlew build --no-daemon
```

The compiled jar will be at `build/libs/FreeL00k-1.0.0.jar`.

## Credits

- **Arthur** -- Developer
- Inspired by Celibistrial/freelook (Fabric) and Atharva-Tiwari12/Freelook-Mod (Forge 1.8.9)
